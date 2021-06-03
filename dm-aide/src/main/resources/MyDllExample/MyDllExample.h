// ²Î¿¼: https://blog.csdn.net/Bryan_QAQ/article/details/92798098
// ²Î¿¼: https://blog.csdn.net/u011563755/article/details/48466607

#ifndef MyDllExample_H_
#define MyDllExample_H_

#ifdef MYDLLLIB
#define MYDLLLIB extern "C" _declspec(dllimport) 
#else
#define MYDLLLIB extern "C" _declspec(dllexport) 
#endif

MYDLLLIB int Add(int a, int b);

MYDLLLIB int Max(int a, int b);

MYDLLLIB int ReturnValue();

MYDLLLIB int ReturnParam(int a, int& b, int& d);

#endif
