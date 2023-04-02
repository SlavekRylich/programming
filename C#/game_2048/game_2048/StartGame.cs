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
        private int bytesread;
        private int toRead;
        private char[] buffer;
        private int offset;
        private string temporary;
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
            Console.WriteLine("Nacitam novou hru...");
            return inputName;

        }
        private void PrintHighScore()
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
            var sortedDict = from entry in highscore orderby entry.Value ascending select entry;
            foreach (var c in sortedDict)
            {
                Console.WriteLine(c);
            }



        }
        public void Start()
        {
            bool run = true;
            while (run)
            {

                Console.WriteLine("Menu");
                Console.WriteLine("1: New Game");
                Console.WriteLine("2: Load Game");
                Console.WriteLine("3: HighScores");
                Console.WriteLine("4: End Game");
                Console.WriteLine("-----------------");
                Console.WriteLine("Press option");
                char inputNum = (char)Console.Read();
                switch (inputNum)
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
