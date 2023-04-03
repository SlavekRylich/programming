using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Linq;
using System.Linq.Expressions;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace game_2048
{
    internal class StartGame
    {
        // Enumerators

        // Constractors
        public StartGame() {
            Start();
            //player.SetScore(game.Run());
        }
        // Variables
        Player player;
        Game game;
        private string path = (System.IO.Directory.GetCurrentDirectory() + @"..\..\..\..\highscores.txt");
        private SortedDictionary<string, ulong> highscore;

        // Properties

        // Methods
        private string NewPlayerName()
        {
            Console.Clear();
            Console.WriteLine("Nova hra");
            Console.WriteLine();
            Console.WriteLine("Zadejte jmeno:");
            string inputName = Console.ReadLine();

            if (highscore.ContainsKey(inputName))
            {
                //Console.Write("{0}: ", _archiv[rok].Rok);
                //for (int i = 0; i < _archiv[rok].MesicniTeploty.Count; i++)
                //{
                //    Console.Write("{0:0.0}; ", _archiv[rok].MesicniTeploty[i]);
                //}
            }
            else
                Console.WriteLine("Nenalezeno");
            Console.WriteLine("Nacitam novou hru...");
            return inputName;

        }
        private void UpdateHighScores()
        {
            
        }
    }
    //private void PrintHighScore()
    //{
    //    using (HighScore highscore  = new HighScore())
    //    {
    //        highscore.PrintHighScore();
    //    }
    //}
    private SortedDictionary<string,ulong> TouchHighScor()
    {
        StreamReader reader = File.OpenText(path);
        highscore = new SortedDictionary<string, ulong>();
        string name;
        ulong score = 0;

        string radek = null;
        while ((radek = reader.ReadLine()) != null)
        {
            string[] values = new string[2];
            values = radek.Split(' ');

            name = values[0];
            score = Convert.ToUInt32(values[1]);
            highscore.Add(name, score);
        }
        reader.Close();
        Console.Clear();
        byte indexer = 0;
        var sortedDict = from entry in highscore orderby entry.Value descending select entry;
        Console.WriteLine(" --- TOP 10 Highscore --- ");     // HEAD of highscore list
        Console.WriteLine("\tJmeno   Body ");
        Console.WriteLine("---------------------------");
        return sortedDict;
    }
        private void PrintHighScore(SortedDictionary<string, ulong> list) 
        { 
            foreach (KeyValuePair<string, ulong> kvp in list)
            {
            byte indexer = 0;
            Console.WriteLine(indexer + 1 + ". {0, 10} {1, 6}",
                    kvp.Key, kvp.Value);
                if (indexer > 10)
                    break;
                indexer++;
            }
            Console.WriteLine("Press any key to return to the main menu.");
            Console.ReadKey();
        }
        public void Start()
        {
            bool run = true;
            while (run)
            {
                Console.Clear();
                Console.WriteLine("Menu");
                Console.WriteLine("1: New Game");
                Console.WriteLine("2: Load Game");
                Console.WriteLine("3: HighScores");
                Console.WriteLine("4: End Game");
                Console.WriteLine("-----------------");
                Console.WriteLine("Press option");
                ConsoleKeyInfo input = Console.ReadKey(true); // BLOCKING TO WAIT FOR INPUT
                switch (input.KeyChar)
                {
                    case '1':
                        player = new Player(NewPlayerName());
                        
                        game = new Game(path);
                        game.Run(player.GetName);
                        break;
                    case '2':
                    //break;
                    case '3':
                        PrintHighScore();
                        break;
                    case '4':
                        run = false;
                        break;
                    
                }
                
            }
        }
    }
}
