/********************************************
 * Cell - Zeigt auf ein Objekt vom Typ Cell	*
 *											*
 *    Author: Tobias Mayer - Mayer25@hm.edu *
 ********************************************/
#include <iostream>
class Cell
{
private:
	Cell* cellPtr;

public:
	Cell(Cell* p){cellPtr = p;};
	virtual ~Cell();

	void virtual display();
	void setCellPtr(Cell* p){cellPtr = p;};
	Cell* getCellPtr(){	return cellPtr;	};
};

