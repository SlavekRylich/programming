using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace game_2048
{
    internal class StartGame
    {
        // Enumerators

        // Constractors
        public StartGame() {
            player = new Player(NewPlayerName());
            //player.SetScore(game.Run());
            game.Run(player.GetName);
        }
        // Variables
        Player player;
        Game game = new Game();
        
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
    }
}
