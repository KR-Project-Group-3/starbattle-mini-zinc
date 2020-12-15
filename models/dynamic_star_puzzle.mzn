%dimension of matrix
int: dim;

set of int: ind = 1..dim;
array[ind, ind] of opt ind: init_sectors;
array[ind, ind] of var ind: sectors;


constraint forall(i, j in ind)(absent(init_sectors[i, j]) \/ init_sectors[i, j] == sectors[i, j]);
constraint forall(k in ind)(sum(i, j in ind) ( bool2int(sectors[i, j] == k) ) >= 2);

solve satisfy;
output [
   show_int(1, sectors[i,j]) ++ if j == dim then "\n" else " " endif | i in ind, j in ind
];


