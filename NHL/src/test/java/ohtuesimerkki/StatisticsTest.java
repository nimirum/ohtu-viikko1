package ohtuesimerkki;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

	Statistics stats;
	Reader readerStub = new Reader() {

		public List<Player> getPlayers() {
			ArrayList<Player> players = new ArrayList<Player>();

			players.add(new Player("Semenko", "EDM", 4, 12));
			players.add(new Player("Lemieux", "PIT", 45, 54));
			players.add(new Player("Kurri", "EDM", 37, 53));
			players.add(new Player("Yzerman", "DET", 42, 56));
			players.add(new Player("Gretzky", "EDM", 35, 89));

			return players;
		}
	};

	@Before
	public void setUp() {
		stats = new Statistics(readerStub);
	}

	@Test
	public void testSearch() {
		Player p = stats.search("Semenko");
		assertNotNull(p);
		assertEquals("Semenko", p.getName());
		p = stats.search("Semenkoooo");
		assertNull(p);
	}

	@Test
	public void testTeam() {
		List<Player> list = stats.team("EDM");
		assertEquals(3, list.size());
		list = stats.team("EDMM");
		assertEquals(0, list.size());
	}
	
	@Test
	public void testTopScores() {
		List<Player> list = stats.topScorers(0);
		assertEquals(1, list.size());
		list = stats.topScorers(4);
		assertEquals(5, list.size());
		assertEquals("Gretzky",list.get(0).getName());
	}

}
