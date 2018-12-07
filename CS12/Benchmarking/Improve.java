public class Improve {

    /**
     * NOTE: THIS FUNCTION IS DEFINED ONLY FOR POSITIVE INTEGERS
     */
    public static int not_really_a_mystery(int n) {
        if (n < 2) return n;
        else return not_really_a_mystery(n-1) + not_really_a_mystery(n-2);
    }

}
