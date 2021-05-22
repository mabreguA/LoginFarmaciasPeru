package com.iteraprocess.framework.utilities;
import com.iteraprocess.framework.config.Settings;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.monte.media.AudioFormatKeys.EncodingKey;
import static org.monte.media.AudioFormatKeys.FrameRateKey;
import static org.monte.media.AudioFormatKeys.KeyFrameIntervalKey;
import static org.monte.media.AudioFormatKeys.MIME_AVI;
import static org.monte.media.AudioFormatKeys.MediaTypeKey;
import static org.monte.media.AudioFormatKeys.MimeTypeKey;
import static org.monte.media.FormatKeys.MediaType;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecordUtil {

    public static ScreenRecordUtil instance = new ScreenRecordUtil();

    private static ScreenRecorder screenRecorder;
    public String moviePath;


    public static void startRecording() {

        try {

            File directory = new File(".");
            String namePath = directory.getCanonicalPath() + File.separator + Settings.VideoPath;

            GraphicsConfiguration gc = GraphicsEnvironment
                    .getLocalGraphicsEnvironment().getDefaultScreenDevice()
                    .getDefaultConfiguration();

            screenRecorder = new ScreenRecorder(gc,
                    new Rectangle(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height),
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null,
                    new File(namePath));
            screenRecorder.start();
        } catch (IOException | AWTException e) {
            e.getMessage();
        }
    }

    public static void stopRecording(String fileName) {

        try {

            File directory = new File(".");
            String namePath = directory.getCanonicalPath() + File.separator + Settings.VideoPath;

            screenRecorder.stop();
            List<File> list = screenRecorder.getCreatedMovieFiles();
            ZonedDateTime date = ZonedDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String fileNameFormat = date.format(formatter);
            list.get(0).renameTo(new File(namePath + fileNameFormat + "-" + fileName + ".avi"));

        } catch (IOException e) {
            e.getMessage();
        }

    }
}
