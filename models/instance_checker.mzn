

%dimension of matrix
int: dim;

%input validation
%constraint assert(num_stars > 0, "num_stars must be positive!");
constraint assert(dim > 0, "dim must be positive!");

set of int: ind_border = 0..dim+1;
set of int: ind = 1..dim;
array[ind, ind] of int: sectors;

constraint forall(i,j in ind)(assert(sectors[i,j] > 0 /\ sectors[i,j] <= dim, "sector values must be between 0 and matrix dimension!"));

array[ind_border,ind_border] of var bool: matrix;
var int: num_stars;

constraint 0 = sum(j in ind_border)(matrix[0,j]);
constraint 0 = sum(j in ind_border)(matrix[dim+1,j]);
constraint 0 = sum(i in ind_border)(matrix[i,0]);
constraint 0 = sum(i in ind_border)(matrix[i,dim+1]);

constraint num_stars > 0;
constraint num_stars < (dim div 2);

%1 numero a riga e 1 a colonna
constraint forall(i in ind)( num_stars = sum(j in ind)(matrix[i,j]) /\ num_stars = sum(j in ind)(matrix[j,i]));

%controllo vicinanze
constraint forall(i in ind, j in ind where matrix[i,j] = 1)(matrix[i,j-1] + matrix[i-1,j-1] + matrix[i-1,j] + matrix[i-1,j+1] + matrix[i,j+1] + matrix[i+1,j+1] + matrix[i+1,j] + matrix[i-1,j+1] = 0);

%constraints zone
constraint forall(i in ind)(sum(i2, j in ind where sectors[i2,j] == i)(matrix[i2,j]) = num_stars);


solve satisfy;

output[show_int(1, num_stars)];