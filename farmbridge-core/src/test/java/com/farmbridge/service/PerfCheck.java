package com.farmbridge.service;

import com.farmbridge.model.BuyerRequirement;
import com.farmbridge.model.ProduceLot;

public class PerfCheck {

    public static void main(String[] args) {
        int n = 1_000_000;
        MatchingService service = new MatchingService();
        ProduceLot lot = new ProduceLot("Tomato", 500, "A");
        BuyerRequirement req = new BuyerRequirement("Tomato", 400, "A");
        double sink = 0;

        for (int round = 1; round <= 3; round++) {
            long t0 = System.nanoTime();
            int hits = 0;
            for (int i = 0; i < n; i++) {
                if (service.matches(lot, req)) {
                    hits++;
                }
            }
            long t1 = System.nanoTime();
            for (int i = 0; i < n; i++) {
                sink += new ProduceLot("Tomato", 500, "A").getQuantityKg();
                sink += new BuyerRequirement("Tomato", 400, "A").getQuantityKg();
            }
            long t2 = System.nanoTime();
            System.out.printf("Round %d: %d matches = %d ms | %d object pairs created = %d ms | hits=%d%n",
                    round, n, (t1 - t0) / 1_000_000, n, (t2 - t1) / 1_000_000, hits);
        }
        System.out.println("sink=" + sink);
    }
}
