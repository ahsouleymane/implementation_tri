<?php

function initialisation()
{
    $init = [];

    for($i = 0; $i < 10000; $i++)
    {
       $init[] = rand(0, 100000);
    }

    return $init;
}

function trier(&$tab, $gauche, $droite)
{
    if(sizeof($tab) < 2)
    {
        return;
    }

    $pivot = $tab[($gauche + $droite) / 2];

    $i = $gauche;
    $j = $droite;
    while($i <= $j)
    {
        while ($tab[$i] < $pivot)
        {
            $i++;
        }

        while ($tab[$j] > $pivot) 
        {
            $j--;
        }

        if($i <= $j)
        {
            $temp = $tab[$i];
            $tab[$i] = $tab[$j];
            $tab[$j] = $temp;
            $i++;
            $j--;
        }
    }
    
    if($gauche < $j)
    {
        trier($tab, $gauche, $j);
    }

    if($i < $droite)
    {
        trier($tab, $i, $droite);
    }
}

$tab1 = initialisation();

print('Le tableau initial: ');
print_r($tab1);

trier($tab1, 0, sizeof($tab1) - 1);

print('Le tableau trié: ');
print_r($tab1);


?>
