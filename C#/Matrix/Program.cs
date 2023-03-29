using cv3;

double[,] a = new double[,] { { 5, 4 }, { 3, 4 } };     // vytvořeno pole jako matice
double[,] b = new double[,] { { 2, 9 }, { 1, 6 } };
double[,] c = new double[,] { { 8, 20, 2 }, {4, 5, 6 }, { 7, 8, 9 } };

Matrix matrix1 = new Matrix(a);             // vytvořen objekt Matrix  a předáno pole do objektu
Matrix matrix2 = new Matrix(b);
Matrix matrix3 = new Matrix(c);


Console.WriteLine(matrix1 + matrix2);
Console.WriteLine(matrix1 - matrix2);
Console.WriteLine(matrix1 * matrix2);
Console.WriteLine(-matrix1);
Console.WriteLine(matrix1 == matrix2);
Console.WriteLine(matrix2 != matrix3);
Console.WriteLine(matrix1.Determinant());
Console.WriteLine(matrix2.Determinant());
Console.WriteLine(matrix3.Determinant());