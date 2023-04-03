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

        public HighScore() { }
        public void PrintHighScore()
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
            foreach (KeyValuePair<string, ulong> kvp in sortedDict)
            {
                Console.WriteLine(indexer + 1 + ". {0, 10} {1, 6}",
                    kvp.Key, kvp.Value);
                if (indexer > 10)
                    break;
                indexer++;
            }
            Console.WriteLine("Press any key to return to the main menu.");
            Console.ReadKey();
        }

        private void SaveToHighscoreFile(ulong score, string namePlayer)
        {
            //string path = (System.IO.Directory.GetCurrentDirectory() + @"-highscores.txt");
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
    }
}
