using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace cv3
{
    public class Matrix
    {
        public double[,] Data { get; set; }             //vlastnost

        public int Rows => Data.GetLength(0);               // pouze čitelné

        public int Columns => Data.GetLength(1);



        public Matrix(double[,] matrix) // konstruktor s výchozími hodnotami
        {
            Data = matrix;

        }

        public static Matrix operator +(Matrix a, Matrix b)
        {
            if (a.Rows != b.Rows || a.Columns != b.Columns)
                throw new ArgumentException("Invalid matrix sizes");

            var result = new double[a.Rows, a.Columns];

            for (var row = 0; row < a.Rows; row++)
                for (var column = 0; column < a.Columns; column++)
                {
                    result[row, column] = a.Data[row, column] + b.Data[row, column];
                }

            return new Matrix(result);
        }

        public static Matrix operator -(Matrix a, Matrix b)
        {
            if (a.Rows != b.Rows || a.Columns != b.Columns)
                throw new ArgumentException("Invalid matrix sizes");

            var result = new double[a.Rows, a.Columns];

            for (var row = 0; row < a.Rows; row++)
                for (var column = 0; column < a.Columns; column++)
                {
                    result[row, column] = a.Data[row, column] - b.Data[row, column];
                }

            return new Matrix(result);
        }

        public static Matrix operator *(Matrix a, Matrix b)
        {
            if (a.Columns != b.Rows)
                throw new ArgumentException("Invalid matrix sizes");

            var result = new double[a.Columns, b.Rows];

            for (var row = 0; row < a.Rows; row++)
                for (var column = 0; column < a.Columns; column++)
                {
                    double temp = 0;
                    for (var curzor = 0; curzor < a.Columns; curzor++)
                    {
                        temp += a.Data[row, curzor] * b.Data[curzor, column];
                    }
                    result[row, column] = temp;
                }
            return new Matrix(result);
        }

        public static bool operator ==(Matrix a, Matrix b)
        {
            if (a.Rows != b.Rows || a.Columns != b.Columns)
                return false;

            for (var row = 0; row<a.Rows; row++)
                for (var column = 0; column<a.Columns; column++)
                {
                   if (a.Data[row, column] != b.Data[row, column])
                        return false;

                }
            
            return true;
     }


        public static bool operator !=(Matrix a, Matrix b)
        {
            return (a == b);
         }

        public static Matrix operator -(Matrix a)
        {
            var result = new double[a.Rows, a.Columns];

            for (var row = 0; row < a.Rows; row++)
                for (var column = 0; column < a.Columns; column++)
                {
                    result[row, column] = -a.Data[row, column];
                }

            return new Matrix(result);
        }

        public double Determinant()
        {
            if (Rows != Columns)
            {
                throw new ArgumentException("Invalid matrix sizes");
            }

            else if (Rows == 1)
            {
                return Data[0,0];
            }

           

            else if (Rows == 2)
            {
                
                return Data[0, 0] * Data[1, 1] - Data[1, 0] * Data[0, 1];
            }

            

            else if (Rows == 3)
            {
                
                    return (((Data[0, 0] * Data[1, 1] * Data[2, 2]) + (Data[1, 0] * Data[2, 1] *
                    Data[0, 2]) + (Data[2, 0] * Data[0, 1] * Data[1, 2])) - ((Data[2, 0] *
                    Data[1, 1] * Data[0, 2]) + (Data[0, 0] * Data[2, 1] * Data[1, 2]) + (Data[1, 0] * Data[0, 1] * Data[2, 2])));
            }
            else
            {
                throw new ArgumentException("More than 3x3 matrix I don´t know to count ");
            }
            
        }


        public override string ToString()
        {
            StringBuilder output=new StringBuilder();

            for (var row = 0; row < Rows; row++)
            {
                for (var column = 0; column < Columns; column++)
                {
                    output.AppendFormat("{0:F2}\t", Data[row, column]);
                }
                output.AppendLine();
            }
            return output.ToString();
        }

    }
}

