package ru.job4j.assertj;

public class Box {

    private static final String UNKNOWN = "Unknown object";
    private int vertex;
    private final int edge;
    private String type = "";

    public Box(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        init();
    }

    public int getVertex() {
        return vertex;
    }

    private void init() {
      switch (vertex) {
            case 0:
                type = "Sphere";
                break;
           case 4:
               type = "Tetrahedron";
               break;
           case 6:
               type = "Cube";
               break;
           default: type = UNKNOWN;
        }
        if (UNKNOWN.equals(type)) {
            vertex = -1;
        }
        if (edge <= 0) {
            vertex = -1;
            type = UNKNOWN;
        }
    }

    public String whatsThis() {
        return this.type;
    }

    public int getNumberOfVertices() {
        return this.vertex;
    }

    public boolean isExist() {
        return this.vertex != -1;
    }

    public double getArea() {
        double a = edge;
        double result;
        switch (vertex) {
            case 0:
                result = 4 * Math.PI * (a * a);
                break;
            case 4:
                result = Math.sqrt(3) * (a * a);
                break;
            case 6:
                result = 6 * (a * a);
                break;
            default:
                result = 0;
        }
        return result;
    }

}
