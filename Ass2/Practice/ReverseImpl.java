import ReverseModule.ReversePOA;

public class ReverseImpl extends ReversePOA {
    ReverseImpl() {
        super();
    }

    @Override
    public String reverse_string(String str) {
        StringBuffer s = new StringBuffer(str);
        s.reverse();
        return ("Server Sent " + s);
    }

}