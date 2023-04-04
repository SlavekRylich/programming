using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace game_2048
{
    internal class HighScore
    {
        private SortedDictionary<string, ulong> highscoreTemp;
        private string path;

        public HighScore(string path) 
        {
            this.path = path;
        }
        private void TouchHighScore()
        {
            StreamReader reader = File.OpenText(path);
            highscoreTemp = new SortedDictionary<string, ulong>();
            string name;
            ulong score = 0;

            string radek = null;
            while ((radek = reader.ReadLine()) != null)
            {
                string[] values = new string[2];
                values = radek.Split(' ');

                name = values[0];
                score = Convert.ToUInt32(values[1]);
                highscoreTemp.Add(name, score);
            }
            reader.Close();
        }
        public void PrintHighScore()
        {

            TouchHighScore();
            Console.Clear();
            byte indexer = 0;
            Console.WriteLine(" --- TOP 10 Highscore --- ");     // HEAD of highscore list
            Console.WriteLine("\tJmeno   Body ");
            Console.WriteLine("---------------------------");
            var sortedHighScore = from entry in highscoreTemp orderby entry.Value descending select entry;
            foreach (KeyValuePair<string, ulong> kvp in sortedHighScore)
            {
                Console.WriteLine(indexer + 1 + ". {0, 10} {1, 6}",
                    kvp.Key, kvp.Value);
                if (indexer > 8)
                    break;
                indexer++;
            }
            Console.WriteLine("Press any key to return to the main menu.");
            Console.ReadKey();
        }

        public void SaveToHighscoreFile(ulong score, string namePlayer)
        {
            if (!File.Exists(path))
            {
                // Create a file to write to.
                using (StreamWriter sw = File.CreateText(path))
                {
                    sw.Write("{0} {1}\n", namePlayer, score);
                    sw.Close();
                }
            }
            else
            {
                using (StreamWriter sw = File.AppendText(path))
                {
                    sw.Write("{0} {1}\n", namePlayer, score);
                    sw.Close();
                }
            }
        }

        public bool Find(string key)
        {
            return highscoreTemp.ContainsKey(key);
        }
    }
}
