import jp.ac.uryukyu.ie.e165741.Judgecard;
import jp.ac.uryukyu.ie.e165741.result;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
/**
 * Created by e1657 on 2017/02/05.
 */
public class resultTest  {
    @Test
    public  void result()throws Exception {
        result.bet(3);
        result.magnification(20);
        result.result();
        assertEquals(240000,result.getmymoney());
    }

}
