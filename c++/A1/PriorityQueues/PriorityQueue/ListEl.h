/********************************************************************
 *  ListEl - kann zusätzliche Datentypen einer Liste definieren		*
 *																	*
 *	Author: Tobias Mayer - Mayer25@hm.edu							*
 *	Usage: Verwaltung der zusätzlichen Datentypen einer Liste		*
 ********************************************************************/
#include "liste.h"
class ListEl : public BaseEl
{
private:
	int additional;
public:
	ListEl(int a,int priority,String name) : BaseEl(priority,name) {additional = a;}
	ListEl(int a,int priority,String name,Cell* ptr) : BaseEl(priority,name){additional = a;}
	void display(){
		cout << priority << name << additional << endl;
	}
};
