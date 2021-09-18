package com.tinkoff.edu.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.*;

public class FileLoanCalcRepository implements LoanCalcRepository {

    @Override
    public UUID save(LoanRequest request) {
        if (request == null) throw new IllegalArgumentException();
        UUID uuid = UUID.randomUUID();
        Path path = Path.of("LoanCalcRepository.csv");
        try {
            Files.writeString(
                    path,
                    uuid.toString() + ", " + request.toString() + ", " + ResponseType.APPROVED + "\n",
                    APPEND,
                    CREATE,
                    WRITE
            );
        } catch (IOException e){
            e.printStackTrace();
        }
        return uuid;
    }
}
