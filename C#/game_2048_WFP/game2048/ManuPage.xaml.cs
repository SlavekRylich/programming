using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace game2048
{
    /// <summary>
    /// Interakční logika pro ManuPage.xaml
    /// </summary>
    public partial class ManuPage : Page
    {
        public ManuPage()
        {
            InitializeComponent();
        }

        private void New_Game_Button_Click(object sender, RoutedEventArgs e)
        {
            Uri uri = new Uri("GamePage.xaml", UriKind.Relative);
            this.NavigationService.Navigate(uri);
            //NavigationService nav = NavigationService.GetNavigationService(this);
            //nav.Navigate(new Uri("GamePage.xaml", UriKind.RelativeOrAbsolute));
        }
    }
}
