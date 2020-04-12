#include <fstream>
#include <iostream>
#include <string>

using namespace std;

class Maze {
public:
	Maze();
	void readMazeArray(ifstream& file);
	void readMazeSize(ifstream& file);
	void printMaze(ofstream& outFile);
	bool move(int oldX, int oldY, int thisX, int thisY);
	void move();
	int findDirection(int thisX, int thisY, int oldX, int oldY);

private:
	int height;
	int width;
	int startX, startY;
	char** mazeArray;
	bool eIsFound;
	int count = 0;

};

int main() {
	ifstream inStream;
	ofstream outStream;
	inStream.open("maze.txt");
	outStream.open("newMaze.txt");
	Maze maze;


	maze.readMazeSize(inStream);
	maze.readMazeArray(inStream);
	maze.move();
	maze.printMaze(outStream);

}

Maze::Maze() :
	height(0), 
	width(0),
	startX(0), 
	startY(0),
	eIsFound(false) {}

//Reads in the maze from the file
void Maze::readMazeArray(ifstream& file) {
	mazeArray = new char*[width];
	char letter;
	for (int i = 0; i < width; i++) {
		mazeArray[i] = new char[height];
	}
	file.get(letter);
	for (int j = 0; j < height; j++) {
		if (j == 0 || j == height - 1) {
			for (int i = 0; i < width; i++) {
				mazeArray[j][i] = '#';
			}
		}
		else {
			for (int i = 0; i < width; i++) {
				if (i == 0 || i == width - 1) {
					mazeArray[j][i] = '#';
				}
				else {
					file.get(letter);
					mazeArray[j][i] = letter;
				}
			}
			file.get(letter);
		}
	}
}

//Gets the maze parameters from the file
void Maze::readMazeSize(ifstream& file) {
	file >> height;
	file >> width;
	file >> startY;
	file >> startX;

	height = height + 2;
	width = width + 2;
	startX++;
	startY++;
}

//Prints the maze to output file
void Maze::printMaze(ofstream& file) {
	for (int j = 0; j < height; j++) {
		for (int i = 0; i < width; i++) {
			file << mazeArray[j][i];
		}
		file << endl;
	}
}

//Finds first availible direction
int Maze::findDirection(int thisX, int thisY, int oldX, int oldY) {
	int x = 0, y = 0;
	int i = 0;
	bool found = false;
	char target = 'O';

	while (!found && i <= 1) {
		if (mazeArray[thisY][thisX + 1] == target && (thisX + 1 != oldX || thisY != oldY)) {
			x = thisX + 1;
			y = thisY;
			found = true;
			cout << "right";
		}
		else if (mazeArray[thisY][thisX - 1] == target && (thisX - 1 != oldX || thisY != oldY)) {
			x = thisX - 1;
			y = thisY;
			found = true;
			cout << "left";
		}
		else if (mazeArray[thisY + 1][thisX] == target && (thisX != oldX || thisY + 1 != oldY)) {
			y = thisY + 1;
			x = thisX;
			found = true;
			cout << "down";
		}
		else if (mazeArray[thisY - 1][thisX] == target && (thisX != oldX || thisY - 1 != oldY)) {
			y = thisY - 1;
			x = thisX;
			found = true;
			cout << "up";
		}
		if (!found) {
			target = 'E';
			i++;
		}
		if (found && target == 'E') {
			eIsFound = true;
		}
	}
	return (x * 100 + y);
}

//The recursion happens here
//"Moves" the position base on the findings of findDirection()
bool Maze::move(int oldX, int oldY, int thisX, int thisY) {

	bool found = false;
	int x, y, xy;

	xy = findDirection(thisX, thisY, oldX, oldY);
	cout << xy << "   :   ";

	if (eIsFound) {
		mazeArray[thisY][thisX] = 'P';
		return true;
	}
	if (xy == 0) {
		mazeArray[thisY][thisX] = 'x';
		return false;
	}
	x = xy / 100;
	y = xy % 100;
	cout << x << "," << y << endl;
	mazeArray[y][x] = 'x';

	found = move(thisX, thisY, x, y);

	if (found) {
		mazeArray[thisY][thisX] = 'P';
		return true;
	}
	else {
		return move(oldX, oldY, thisX, thisY);
	}
}

//Fakes the old coords in cases where only one coord is passed.
void Maze::move() {
	move(startX - 1, startY - 1, startX, startY);
}