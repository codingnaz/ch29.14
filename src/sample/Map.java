package sample;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Map extends Application {

	String[] verticessss = {"Seattle", "San Francisco", "Los Angeles",
			"Denver", "Kansas City", "Chicago", "Boston", "New York",
			"Atlanta", "Miami", "Dallas", "Houston"};
	List<City> newCilylist = new ArrayList<City>();
	City[] vertices = {
	new City("Seattle", 75, 50),
	new City("San Francisco", 50, 210),
	new City("Los Angeles", 75, 275),
	new City("Denver", 275, 175),
	new City("Kansas City", 400, 245),
	new City("Chicago", 450, 100),
	new City("Boston", 700, 80),
	new City("New York", 675, 120),
	new City("Atlanta", 575, 295),
	new City("Miami", 600, 400),
	new City("Dallas", 408, 325),
	new City("Houston", 450, 360)
	};

	int[][] edges = {
	{0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
	{1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
	{2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
	{3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599},
	{3, 5, 1003},
	{4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260},
	{4, 8, 864}, {4, 10, 496},
	{5, 0, 2097}, {5, 3, 1003}, {5, 4, 533},
	{5, 6, 983}, {5, 7, 787},
	{6, 5, 983}, {6, 7, 214},
	{7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
	{8, 4, 864}, {8, 7, 888}, {8, 9, 661},
	{8, 10, 781}, {8, 11, 810},
	{9, 8, 661}, {9, 11, 1187},
	{10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
	{11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
	};
	WeightedGraph<String> graph1 = new WeightedGraph<>(verticessss, edges);
	WeightedGraph<String>.MST tree1 = graph1.getMinimumSpanningTree();
	WeightedGraph<City> weightedGraph = new WeightedGraph<>(vertices, edges);
	ArrayList<lineName> listoflineName= tree1.PrintTreeArray();
	GraphView graphView = new GraphView(weightedGraph);



@Override
public void start(Stage primaryStage) {
	Scene scene = new Scene(graphView, 750, 450);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Nazmul's Ch29.14");
	primaryStage.show();

	}

public class GraphView extends Pane {
	Graph<? extends Displayable> graph;

public GraphView(Graph<? extends Displayable> graph) {
	this.graph = graph;
	view();
	}


public void view() {
	List<? extends Displayable> vertices = graph.getVertices();
	for (int i = 0; i < graph.getSize(); i++) {
	int x = vertices.get(i).getX();
	int y = vertices.get(i).getY();
	String name = vertices.get(i).getName();
	getChildren().addAll(new Circle(x, y, 8),
	new Text(x - 12, y - 12, name));
	}

	for (int i = 0; i < graph.getSize(); i++) {
	List<Integer> neighbors = graph.getNeighbors(i);
	for (int i2 = 0; i2 < neighbors.size(); i2++) {
	int v = neighbors.get(i2);
	String name= graph.getVertex(i).getName();
	int x1 = graph.getVertex(i).getX();
	int y1 = graph.getVertex(i).getY();
	int x2 = graph.getVertex(v).getX();
	int y2 = graph.getVertex(v).getY();
	try {
	int x3 = (x1 + x2) / 2;
	int y3 = ((y1 + y2) / 2) - 6;
	String text = (" " + ((WeightedGraph<? extends Displayable>)graph).getWeight(i, v) + " ");
	Line newline= new Line(x1, y1, x2, y2);
	//newline.setStroke(Color.RED);
	getChildren().addAll(newline, new Text(x3, y3, text));

	}
	catch (Exception ex) {
	ex.printStackTrace();
	}
	}
	}
	for(int i=0;i<listoflineName.size();i++){
		for(int k=0;k< graph.getSize();k++){
			if(listoflineName.get(i).getF().equals(graph.getVertex(k).getName())){
				newCilylist.add(new City((String) graph.getVertex(k).getName(), (int)graph.getVertex(k).getX(),(int)graph.getVertex(k).getY()));

			}
		}
		//Line newline= new Line(listoflineName.get(i).getF(), y1, x2, y2);
	}
	for(int j=0; j<newCilylist.size()-1;j++){

		Line newRedLine= new Line(newCilylist.get(j).getX(), newCilylist.get(j).getY(),newCilylist.get(j+1).getX(), newCilylist.get(j+1).getY());
		newRedLine.setStroke(Color.RED);
		getChildren().addAll(newRedLine);
	}







	}
	}

static class City implements Displayable {
	private int x, y;
	private String name;

City(String name, int x, int y) {
	this.name = name;
	this.x = x;
	this.y = y;
	}

@Override
public int getX() {
	return x;
	}

@Override
public int getY() {
	return y;
	}

@Override
public String getName() {
	return name;
	}
	}

public static void main(String[] args) {
	launch(args);
	}

}
