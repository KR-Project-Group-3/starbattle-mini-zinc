cell(I,J) :- row(I), column(J).
inMatrix(I,J,S)|outMatrix(I,J,S) :- cell(I,J), sector(S).

reaches(I,J,I,J) :- inMatrix(I,J,S).
reaches(I,J,I2,J) :- inMatrix(I,J,S), inMatrix(I2,J,S), I2 = I+1.
reaches(I,J,I2,J) :- inMatrix(I,J,S), inMatrix(I2,J,S), I2 = I-1.
reaches(I,J,I,J2) :- inMatrix(I,J,S), inMatrix(I,J2,S), J2 = J+1.
reaches(I,J,I,J2) :- inMatrix(I,J,S), inMatrix(I,J2,S), J2 = J-1.

reaches(I,J,I3,J3) :- reaches(I,J,I2,J2), reaches(I2,J2,I3,J3).

:- inMatrix(I,J,S), inMatrix(I2,J2,S), not reaches(I,J,I2,J2).


:- cell(I,J), not #count{S:inMatrix(I,J,S)}=1.

:- #count{S:inMatrix(I,J,S)}=M, #count{S:sector(S)}=N, M!=N.

:- sector(S), #count{I,J:inMatrix(I,J,S)} < 2.
