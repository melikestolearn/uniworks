function m = aufgabe2(ordnung) %returns a matrix with some zeros
m = ones(ordnung);
m = m + eye(ordnung,ordnung);
counter = ordnung-1
    while counter >=1
    v = 1:ordnung-counter;
    if (counter>1)
    v (1:end) = 1;
    else
        v(1:end) = 2;
    end
    m = m-diag(v,counter);
    m = m-diag(v,-counter);
    counter = counter-1
end