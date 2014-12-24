import java.util.ArrayList;
import java.util.List;

public class Cell{
    private int value;
    private boolean assigned;
    private List<Integer> possibles;

    //Value is 0 if the cell is not definitely assigned, otherwise it is the value of the cell
    //Assigned is true if the cell is definitely assigned, false otherwise
    //possibles is a list of integers that that the cell could be, if it is not already assigned
    public Cell(int v){
        value = v;
        possibles = new ArrayList<Integer>();
        assigned = (v != 0);
    }

    //This constructor used to clone a Cell
    public Cell(Cell c){
        this.value = c.getValue();
        this.assigned = c.isAssigned();
        this.possibles = new ArrayList<Integer>();
        if (!this.assigned){
            for (int i = 0; i < c.getPossibles().size(); i++){
                this.possibles.add(new Integer(c.getPossibles().get(i)));
            }
        }
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPossibles(List<Integer> possibles) {
        this.possibles = possibles;
    }

    public int getValue() {
        return value;
    }

    public List<Integer> getPossibles() {
        return possibles;
    }
}