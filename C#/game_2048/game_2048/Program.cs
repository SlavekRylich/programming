using game_2048;



Console.WriteLine("Menu");
Console.WriteLine("1: New Game");
Console.WriteLine("2: Load Game");
Console.WriteLine("3: HighScores");
Console.WriteLine("4: End Game");
Console.WriteLine("-----------------");
Console.WriteLine("Press option");
string inputName = Console.ReadKey().ToString();
StartGame game = new StartGame();