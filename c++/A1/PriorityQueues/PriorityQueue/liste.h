/****************************************************
 * Header-Datei liste.h 							*
 * Enthält die Klassen Cell,BaseEl und ListEl		*
 * zur darstellung der Elemente einer liste			*
 ****************************************************/

/********************************************
 * Cell - Zeigt auf ein Objekt vom Typ Cell	*
 *											*
 *    Author: Tobias Mayer - Mayer25@hm.edu *
 ********************************************/
#include <string>
using namespace std;
#define null 0

class Cell
{
private:
	Cell* cellPtr;

public:
	Cell(Cell* p){cellPtr = p;}
	virtual ~Cell();

	void virtual display();
	void setCellPtr(Cell* p){cellPtr = p;}
	Cell* getCellPtr(){	return cellPtr;	}
};

/************************************************************
 * BaseEl - darstellung einer Priorität und eines Namens	*
 * 															*
 *      Author: Tobias Mayer - Mayer25@hm.edu				*
 * Usage: Verwaltung der Basis eines Listenelements			*
 ************************************************************/

template<class T> class BaseEl : public Cell
{
private:
	T priority;
	string name;
public:
	BaseEl(T p,string n) : Cell(null){priority = p;name = n;}
	BaseEl(T p,string n,Cell* ptr) : Cell(ptr){priority = p;name = n;}
	void setPriority(T p){priority = p;}
	T getPriority(){return priority;}
	string getName () {return name;}
	void display(){	cout << "Priorität: " << priority << "Name: "<< name << "\n";}
};

/********************************************************************
 *  ListEl - kann zusätzliche Datentypen einer Liste definieren		*
 *																	*
 *	Author: Tobias Mayer - Mayer25@hm.edu							*
 *	Usage: Verwaltung der zusätzlichen Datentypen einer Liste		*
 ********************************************************************/

template<class T,class U> class ListEl: public BaseEl
{
private:
	T additional;
public:
	ListEl(T a,U priority,string name) : BaseEl(priority,name) {additional = a;}
	ListEl(T a,U priority,string name,Cell* ptr) : BaseEl(priority,name,ptr){additional = a;}
	void setAdditional(T a){ additional = a;}
	T getAdditional(){return additional;}
	void display(){	cout << additional << "\n";	}
};


