/* Name of the class has to be "Main" only if the class is public. */
public class Matches {
private int firstMatchIndex;
private int numberOfMatches;
public Matches(int firstMatchIndex, int numberOfMatches) {
this.firstMatchIndex = firstMatchIndex;
this.numberOfMatches = numberOfMatches;
}
public int getFirstMatchIndex() {
return this.firstMatchIndex;
}
public int getNumberOfMatches() {
return this.numberOfMatches;
}
}