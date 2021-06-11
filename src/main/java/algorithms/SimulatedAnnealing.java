package algorithms;

public class SimulatedAnnealing extends Algorithm{

    private int tolerance;
    private double temperature;
    private AnnealingState currentState;
    private AnnealingState nextState;

    public SimulatedAnnealing(int n, PieceType pieceType, double temperature) {
        super(n, pieceType);
        this.tolerance = 0;
        this.temperature = temperature;
        this.chessboardManager.fillBoardOnePiecePerColumn();
        this.currentState = new AnnealingState(this.chessboard, this.chessboardManager);
    }

    @Override
    public AlgorithmOutput run() {
        long startTime = System.nanoTime();

        boolean success = simulatedAnnealing();

        long elapsedNanoSeconds = System.nanoTime() - startTime;
        return new AlgorithmOutput(currentState.getChessboard(), elapsedNanoSeconds, success);

    }

    private boolean simulatedAnnealing() {
        while(!isCurrentStateSuccessful()){
            double temperature;
            double delta;
            double probability;
            double rand;

            for (temperature = this.temperature; (temperature > 0) && (currentState.getCost() != 0); temperature--) {
                nextState = currentState.getNextState();

                delta = currentState.getCost() - nextState.getCost();
                probability = Math.exp(delta / temperature);
                rand = Math.random();

                if (delta > 0) {
                    currentState = nextState;
                } else if (rand <= probability) {
                    currentState = nextState;
                }
            }
        }
        return true;
    }

    private boolean isCurrentStateSuccessful() {
        return currentState.getCost() == 0;
    }
}
