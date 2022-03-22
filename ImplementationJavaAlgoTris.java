import java.util.Scanner;

public class ImplementationJavaAlgorithmesTris
{
    static  float [] table  =  new  float [10000]  ;  // le tableau à trier en attribut


    static void  affichage  ( )
    {
        // Affichage sans les sentinelles
        int  n  =  table.length - 2 ;
        for (  int  i  =  1 ;  i <= n ;  i ++ )
            System.out.print ( table[i] + " , ");
        System.out.println ();
    }

    static void  initialisation   ( )
    {
        // remplissage aléatoire du tableau
        int  n  =  table.length - 2 ;
        for( int  i  =  1 ;  i <= n ;  i ++ )
            table[i]  =  ( float ) ( Math.random () * 10000 );
        // Les sentinelles:
        table[0]  = - Integer.MAX_VALUE ;
        table[n + 1]  =  Integer.MAX_VALUE ;
    }

    // ----> Tri rapide :

    /* ########## TRI RAPIDE ########## */

    static  int  partition  ( int  gauche,  int  droite  )
    {  // partition
        int  i, j ;
        float pivot, temp;
        pivot  =  table[droite] ;
        i  =  gauche - 1 ;
        j  =  droite ;
        do
        {
            do
                i ++ ;
            while ( table[i] < pivot );

            do
                j -- ;
            while ( table[j] > pivot );

            temp  =  table[i] ;
            table[i]  =  table[j] ;
            table[j]  =  temp ;
        }
        while( j > i );

        table[j]  =  table[i] ;
        table[i]  =  table[droite] ;
        table[droite]  =  temp ;

        return  i ;
    }

    static void  triRapide  ( int  gauche,  int  droite  )
    {  // tri rapide, sous-programme récursif
        int  i ;
        if( droite > gauche )
        {
            i  =  partition ( gauche, droite );
            triRapide ( gauche,i - 1 );
            triRapide ( i + 1, droite );
        }
    }

    /* ########## TRI FUSION ########## */

    static void triFusion()
    {
        int longueur =table.length;
        if(longueur > 0)
        {
            triFusion(0,longueur-1);
        }
    }

    static void triFusion(int deb, int fin)
    {
        if(deb != fin)
        {
            int milieu = (fin+deb) / 2;
            triFusion(deb,milieu);
            triFusion(milieu+1,fin);
            fusion(deb,milieu,fin);
        }
    }

    static void fusion(int deb1, int fin1, int fin2)
    {
        int deb2 = fin1 + 1;

        // On recopie les elements du debut du tableau
        float [] table1 = new float[fin1 - deb1 + 1];
        for(int i = deb1; i <= fin1; i++)
        {
            table1[i - deb1] = table[i];
        }

        int compt1 = deb1;
        int compt2 = deb2;

        for(int i = deb1; i <= fin2; i++)
        {
            if(compt1 == deb2) // tout les elts du premier tableau ont été utilisés
            {
                break; // tout les elts ont donc été classés
            }
            else if(compt2 == (fin2 + 1)) // tout les elts du second tableau ont été utilisés
            {
                table[i] = table1[compt1 - deb1]; // on ajoute les elts restants du premier tableau
                compt1++;
            }
            else if(table1[compt1 - deb1] < table[compt2])
            {
                table[i] = table1[compt1 - deb1]; // on ajoute un elt du premier tableau
                compt1++;
            }
            else
            {
                table[i] = table[compt2]; // on ajoute un elt du second tableau
                compt2++;
            }
        }
    }

    /* ########## TRI INSERTION ########## */

    static void triInsertion()
    {
        int taille = table.length;

        for (int i = 1; i < taille; i++)
        {
            float index = table[i];
            int j = i-1;

            while(j >= 0 && table[j] > index)
            {
                table[j+1] = table[j];
                j--;
            }
            table[j+1] = index;
        }
    }
    /* ########## TRI SELECTION ########## */

    static void triSelection()
    {
        for (int i = 0; i < table.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < table.length; j++)
            {
                if (table[j] < table[index]){
                    index = j;
                }
            }

            float min = table[index];
            table[index] = table[i];
            table[i] = min;
        }
    }

    /* ########## TRI BULLE ########## */

    static void triBulle()
    {
        int taille = table.length;
        float tmp = 0;
        for(int i=0; i < taille; i++)
        {
            for(int j=1; j < (taille-i); j++)
            {
                if(table[j-1] > table[j])
                {
                    //echanges des elements
                    tmp = table[j-1];
                    table[j-1] = table[j];
                    table[j] = tmp;
                }

            }
        }
    }

    /* ########## METHODE MAIN ########## */

    public static void  main ( String[ ] args )
    {
        System.out.println("Choisir une methode de tri: ");
        System.out.println("Pour le tri rapide, taper 1: ");
        System.out.println("Pour le tri fusion, taper 2: ");
        System.out.println("Pour le tri insertion, taper 3: ");
        System.out.println("Pour le tri selection, taper 4: ");
        System.out.println("Pour le tri bulle, taper 5: ");
        System.out.println("Pour le temps d'execution de chaque tri, taper 2022:");
        System.out.println("Pour sortir du programme, taper 0: \n");

        Scanner sc = new Scanner(System.in);
        System.out.print("Saisir un numero: ");
        String methode = sc.next();
        System.out.print("\n");

        switch (methode)
        {
            case "0":
                break;
            case "1":
                System.out.println("\n##### TRI RAPIDE #####\n");
                initialisation  ( );
                System.out.println ("Tableau initial :");
                affichage ( );
                int  n  =  table.length - 2 ;
                triRapide ( 1,n );
                System.out.println ("Tableau une fois trié :");
                affichage  ( );
                break;
            case "2":
                System.out.println("\n##### TRI FUSION #####\n");
                initialisation  ( );
                System.out.println ("Tableau initial :");
                affichage ( );
                triFusion ();
                System.out.println ("Tableau une fois trié :");
                affichage  ( );
                break;
            case "3":
                System.out.println("\n##### TRI INSERTION #####\n");
                initialisation  ( );
                System.out.println ("Tableau initial :");
                affichage ( );
                triInsertion ();
                System.out.println ("Tableau une fois trié :");
                affichage  ( );
                break;
            case "4":
                System.out.println("\n##### TRI SELECTION #####\n");
                initialisation  ( );
                System.out.println ("Tableau initial :");
                affichage ( );
                triSelection ();
                System.out.println ("Tableau une fois trié :");
                affichage  ( );
                break;
            case "5":
                System.out.println("\n##### TRI BULLE #####\n");
                initialisation  ( );
                System.out.println ("Tableau initial :");
                affichage ( );
                triBulle ();
                System.out.println ("Tableau une fois trié :");
                affichage  ( );
                break;
            case "2022":
                long tempsDebut, tempsFin;
                double seconds;

                System.out.println("##### Temps d'execution tri rapide #####");
                n = table.length - 2;
                initialisation();
                tempsDebut  = System.currentTimeMillis();
                triRapide ( 1,n );
                tempsFin = System.currentTimeMillis();
                seconds = (tempsFin - tempsDebut)/1000F;
                System.out.println("Operation Effectuée en: " + Double.toString(seconds) + " secondes.\n");

                System.out.println("##### Temps d'execution tri fusion #####");
                initialisation();
                tempsDebut  = System.currentTimeMillis();
                triFusion();
                tempsFin = System.currentTimeMillis();
                seconds = (tempsFin - tempsDebut)/1000F;
                System.out.println("Operation Effectuée en: " + Double.toString(seconds) + " secondes.\n");

                System.out.println("##### Temps d'execution tri insertion #####");
                initialisation();
                tempsDebut  = System.currentTimeMillis();
                triInsertion();
                tempsFin = System.currentTimeMillis();
                seconds = (tempsFin - tempsDebut)/1000F;
                System.out.println("Operation Effectuée en: " + Double.toString(seconds) + " secondes.\n");

                System.out.println("##### Temps d'execution tri selection #####");
                initialisation();
                tempsDebut  = System.currentTimeMillis();
                triSelection();
                tempsFin = System.currentTimeMillis();
                seconds = (tempsFin - tempsDebut)/1000F;
                System.out.println("Operation Effectuée en: " + Double.toString(seconds) + " secondes.\n");

                System.out.println("##### Temps d'execution tri bulle #####");
                initialisation();
                tempsDebut  = System.currentTimeMillis();
                triBulle();
                tempsFin = System.currentTimeMillis();
                seconds = (tempsFin - tempsDebut)/1000F;
                System.out.println("Operation Effectuée en: " + Double.toString(seconds) + " secondes.");

                break;
            default:
                System.out.println("La valeur saisi n'est pas valide.");
                break;
        }
    }
}

