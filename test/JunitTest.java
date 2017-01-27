import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class JunitTest {

	@Test
	public void test() throws IOException {
		Parlament parlament = new ParlamentBuilder().build(8);
		List <Posel> poslowie = parlament.getParlament();
		
		Podroze podroze_poslow = new Podroze();
		
		assertEquals(podroze_poslow.najwiecejPodrozy(poslowie), 87);
		
	}

}
