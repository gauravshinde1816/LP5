import ReverseModule.ReversePOA;
public class ReverseImpl extends ReversePOA{

    @Override
    public String reverse_string(String str) {
        StringBuilder s = new StringBuilder(str);
        s.reverse();
        return  "Server Returns " + s;
    }

}