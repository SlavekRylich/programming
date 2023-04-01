using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace game_2048
{
    internal class Player
    {
        // Enumerators

        // Constractors

        public Player(string inName, int inScore = 0 ) {
            name = inName.ToLower();
            score = inScore;
        }
        // Variables

        private string name;
        private int score;

        // Properties
        public string GetName { get { return name; } }

        public int SetScore { get { return score; } set { score = value; } }
        // Methods
    }
}
