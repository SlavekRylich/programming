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
        }

        // Variables
        Player player;
        Game game;
        private string path = (System.IO.Directory.GetCurrentDirectory() + @"..\..\..\..\highscores.txt");
        //private SortedDictionary<string, ulong> highscore;

        // Properties

        // Methods
        private string NewPlayerName(HighScore highScore)
        {
            Console.Clear();
            Console.WriteLine("Nova hra");
            Console.WriteLine();
            bool validName = false;
            string inputName=null;
            Console.WriteLine("Zadejte jmeno:");
            while (!validName)
            {
                
                inputName = Console.ReadLine();

                if (highScore.Find(inputName))
                {
                    Console.WriteLine("Jmeno je obsazene");
                    Console.WriteLine("Zadejte jmeno znovu a lepe:");
                }
                else
                {
                    validName = true;   
                    Console.WriteLine("Nacitam novou hru...");
                }
            }
            return inputName;
        }
        private void UpdateHighScores()
        {
            
        }
    
    
        public void Start()
        {
            HighScore highScore = new HighScore(path);
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
                        player = new Player(NewPlayerName(highScore));
                        
                        game = new Game(path);
                        highScore.SaveToHighscoreFile(game.Run(player.GetName), player.GetName);
                        break;
                    case '2':
                    //break;
                    case '3':
                        highScore.PrintHighScore();
                        break;
                    case '4':
                        run = false;
                        break;
                    
                }
                
            }
        }
    }
}
