package org.ucb.c5.sequtils;

/**
 * Calculates the reverse complement of a DNA sequence
 *
 * @author J. Christopher Anderson
 */
public class RevComp {

    /**
     * Calculates the reverse complement of a DNA sequence preserving the case
     * of each character
     *
     * @param dna the DNA sequence that should be reverse complemented
     * @return the reverse complement of dna
     */
    public String run(String dna) {
        StringBuilder sb = new StringBuilder();
        for (int i = dna.length() - 1; i >= 0; i--) {
            char achar = dna.charAt(i);
            if (achar == 'A') {
                sb.append("T");
            } else if (achar == 'T') {
                sb.append("A");
            } else if (achar == 'C') {
                sb.append("G");
            } else if (achar == 'G') {
                sb.append("C");
            } else if (achar == 'a') {
                sb.append("t");
            } else if (achar == 't') {
                sb.append("a");
            } else if (achar == 'c') {
                sb.append("g");
            } else if (achar == 'g') {
                sb.append("c");
            } else if (achar == 'B') {
                sb.append("V");
            } else if (achar == 'D') {
                sb.append("H");
            } else if (achar == 'H') {
                sb.append("D");
            } else if (achar == 'K') {
                sb.append("M");
            } else if (achar == 'N') {
                sb.append("N");
            } else if (achar == 'R') {
                sb.append("Y");
            } else if (achar == 'S') {
                sb.append("S");
            } else if (achar == 'V') {
                sb.append("B");
            } else if (achar == 'W') {
                sb.append("W");
            } else if (achar == 'Y') {
                sb.append("R");
            } else if (achar == 'b') {
                sb.append("v");
            } else if (achar == 'd') {
                sb.append("h");
            } else if (achar == 'h') {
                sb.append("d");
            } else if (achar == 'k') {
                sb.append("m");
            } else if (achar == 'n') {
                sb.append("n");
            } else if (achar == 'r') {
                sb.append("y");
            } else if (achar == 's') {
                sb.append("s");
            } else if (achar == 'v') {
                sb.append("b");
            } else if (achar == 'w') {
                sb.append("w");
            } else if (achar == 'y') {
                sb.append("r");
            }
        }

        return sb.toString();
    }
}
