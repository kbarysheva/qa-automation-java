package com.tinkoff.edu.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static java.nio.file.StandardOpenOption.*;

public class FileLoanCalcRepository implements LoanCalcRepository {

    Path path = Path.of("LoanCalcRepository.csv");

    public ResponseType getStatusByUUID (Object uuid) {
        if (uuid == null) throw new IllegalArgumentException();
        try {
            final List<String> strings = Files.readAllLines(path);
            for (String string : strings) {
                if (string.contains(uuid.toString())) {
                    String[] responseParts = string.split(";");
                    return ResponseType.valueOf(responseParts[responseParts.length - 1].replaceAll("\\s+", ""));
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            return ResponseType.UNKNOWN;
        }
        return ResponseType.UNKNOWN;
    }

    public boolean setStatusByUUID (Object uuid, ResponseType responseType) {
        if (uuid == null || responseType == null) throw new IllegalArgumentException();

        return false;
    }

    @Override
    public UUID save(LoanRequest request) {
        if (request == null) throw new IllegalArgumentException();
        UUID uuid = UUID.randomUUID();
        try {
            Files.writeString(
                    path,
                    uuid.toString() + "; " + request.toString() + "; " + ResponseType.APPROVED + "\n",
                    APPEND,
                    CREATE,
                    WRITE
            );
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return uuid;
    }
}
