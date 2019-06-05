package ru.itvitality.otus.java.runner;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessRunnerImpl implements ProcessRunner {
    private final StringBuffer out = new StringBuffer();
    private Process process;


    @Override
    public void start(String command) throws IOException {
        process = runProcess(command);
    }

    @Override
    public void stop() {
        process.destroy();
    }

    @Override
    public String getOutput() {
        return out.toString();
    }

    private Process runProcess(String command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        StreamListener output = new StreamListener(process.getInputStream(), "OUTPUT");
        output.start();

        return process;
    }


    private class StreamListener extends Thread {
        private final InputStream inputStream;
        private final String type;

        private StreamListener(InputStream is, String type) {
            this.inputStream = is;
            this.type = type;
        }

        @Override
        public void run() {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)){
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while((line = bufferedReader.readLine()) !=  null){
                    out.append(type).append('>').append(line).append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
