import java.util.ArrayList;

public class Ghost {
  String myName;
  Location myLoc;
  Map myMap;

  public Ghost(String name, Location loc, Map map) {
    this.myLoc = loc;
    this.myName = name;
    this.myMap = map;
  }

  public ArrayList<Location> get_valid_moves() {
    ArrayList<Location> moves = new ArrayList<Location>();
    // NORTH
    if(!myMap.getLoc(myLoc.shift(0, 1)).contains(Map.Type.WALL)) {
      moves.add(myLoc.shift(0, 1));
    }
    // EAST
    if(!myMap.getLoc(myLoc.shift(1, 0)).contains(Map.Type.WALL)) {
      moves.add(myLoc.shift(1, 0));
    }
    // WEST
    if(!myMap.getLoc(myLoc.shift(-1, 0)).contains(Map.Type.WALL)) {
      moves.add(myLoc.shift(-1, 0));
    }
    // SOUTH
    if(!myMap.getLoc(myLoc.shift(0, -1)).contains(Map.Type.WALL)) {
      moves.add(myLoc.shift(0, -1));
    }
    return moves;
  }

  public boolean move() {
    ArrayList<Location> valid_moves = this.get_valid_moves();
    if (valid_moves.size() == 0) {
      return false;
    }
    int ran_index = (int)(Math.random() * valid_moves.size());
    this.myLoc = valid_moves.get(ran_index);
    return this.myMap.move(this.myName, this.myLoc, Map.Type.GHOST);
  }

  public boolean is_pacman_in_range() {
    return (myMap.getLoc(myLoc.shift(0, 1)).contains(Map.Type.PACMAN)
    || myMap.getLoc(myLoc.shift(1, 0)).contains(Map.Type.PACMAN)
    || myMap.getLoc(myLoc.shift(-1, 0)).contains(Map.Type.PACMAN)
    || myMap.getLoc(myLoc.shift(0, -1)).contains(Map.Type.PACMAN)
    || myMap.getLoc(myLoc).contains(Map.Type.PACMAN)) ? true : false;
  }

  public boolean attack() {
    if (is_pacman_in_range()) {
      return myMap.attack(myName);
    }
    return false;
  }
}
