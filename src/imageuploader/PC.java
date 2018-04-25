/*
 * Copyright (c) [2018] [Jonathan McIntosh, Martin Staadecker, Ryan Zazo]
 */

package imageuploader;

import common.Config;
import common.logger.Logger;
import lejos.utility.Delay;

import java.io.*;
import java.net.Socket;

/**
 * Uploads an image to the EV3
 * Not pretty but works
 */
class PC {
    private static final String LOG_TAG = PC.class.getSimpleName();

    public static void main(String[] args) {

        OutputStream outputStream = getOutputStream();

        if (outputStream == null) {
            Logger.error(LOG_TAG, "Failed to connect");
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Config.DATA_PC_PATH)));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            while (true) {
                bufferedWriter.write(bufferedReader.readLine());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            Logger.error(LOG_TAG, "Failed");
        }
    }

    private static OutputStream getOutputStream() {
        Logger.info(LOG_TAG, "Attempting to createOutputStream to EV3 ...");
        for (int attempt = 0; attempt < 6; attempt++) {
            try {
                OutputStream outputStream = new Socket(Config.EV3_IP_ADDRESS, Config.PORT_TO_CONNECT_ON_EV3).getOutputStream();

                Logger.info(LOG_TAG, "Connected to PCDataSender");

                return outputStream;

            } catch (IOException e) {
                Delay.msDelay(3000);
            }
        }

        Logger.warning(LOG_TAG, "Failed to createOutputStream");

        return null;
    }
}
