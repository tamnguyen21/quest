package demo.composant;

public class Game {

	private Graphisme graphisme;
	private IConfig configAudio;
	
	public Game() {}

	public Graphisme getGraphisme() {
		return graphisme;
	}

	public void setGraphisme(Graphisme graphisme) {
		this.graphisme = graphisme;
	}

	public IConfig getConfigAudio() {
		return configAudio;
	}

	public void setConfigAudio(IConfig configAudio) {
		this.configAudio = configAudio;
	}

	@Override
	public String toString() {
		return "Game [graphisme=" + graphisme + ", configAudio=" + configAudio + "]";
	}
	
	
}
