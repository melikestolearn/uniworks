/************************************************************
 * BaseEl - darstellung einer Priorität und eines Namens	*
 * 															*
 *      Author: Tobias Mayer - Mayer25@hm.edu				*
 * Usage: Verwaltung der Basis eines Listenelements			*
 ************************************************************/
#include "liste.h"

typdef int PRIORITY;		//Datentyp von priority
class BaseEl : public Cell
{
private:
	PRIORITY priority;
	String name;
public:
	BaseEl(PRIORITY p,String n) : Cell(null){priority = p;name = n;}
	BaseEl(PRIORITY p,String n,Cell* ptr) : Cell(ptr){priority = p;name = n;}
	void display(){
		std::cout << priority << "\n" << name << endl;
	}
};
