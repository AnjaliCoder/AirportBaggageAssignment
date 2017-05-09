package org.airport.route.model;


/**
 * the Conection class ( edge for algo class)
 */
public class Connection {
	
	private final BaggageTerminal sourceTerminal;
	private final BaggageTerminal destinationTerminal;
	private int travelTime;
	
	
	public Connection(BaggageTerminal sourceTerminal, BaggageTerminal destinationTerminal, int travelTime) {
		this.sourceTerminal = sourceTerminal;
		this.destinationTerminal = destinationTerminal;
		this.travelTime = travelTime;
	}


	/**
	 * @return the sourceTerminal
	 */
	public BaggageTerminal getSourceTerminal() {
		return sourceTerminal;
	}


	/**
	 * @return the destinationTerminal
	 */
	public BaggageTerminal getDestinationTerminal() {
		return destinationTerminal;
	}


	/**
	 * @return the weight
	 */
	public int getWeight() {
		return travelTime;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinationTerminal == null) ? 0 : destinationTerminal.hashCode());
		result = prime * result + ((sourceTerminal == null) ? 0 : sourceTerminal.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		if (destinationTerminal == null) {
			if (other.destinationTerminal != null)
				return false;
		} else if (!destinationTerminal.equals(other.destinationTerminal))
			return false;
		if (sourceTerminal == null) {
			if (other.sourceTerminal != null)
				return false;
		} else if (!sourceTerminal.equals(other.sourceTerminal))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConveyorBelt [sourceTerminal=" + sourceTerminal.getName() + ", destinationTerminal=" + destinationTerminal.getName()
				+ ", weight=" + travelTime + "]";
	}

	

}
