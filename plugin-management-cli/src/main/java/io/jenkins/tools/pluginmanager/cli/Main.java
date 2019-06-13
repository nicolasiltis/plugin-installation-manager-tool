package io.jenkins.tools.pluginmanager.cli;

import io.jenkins.tools.pluginmanager.cli.CliOptions;
import io.jenkins.tools.pluginmanager.impl.PluginManager;
import io.jenkins.tools.pluginmanager.config.Config;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        CliOptions options = new CliOptions();
        CmdLineParser parser = new CmdLineParser(options);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            parser.printUsage(System.out);
            throw new IOException("Failed to read command-line arguments", e);
        }

        Config cfg = new Config();

        cfg.setPluginTxt(options.getPluginTxt());
        cfg.setPluginDir(options.getPluginDir());
        cfg.setShowWarnings(options.hasShowWarnings());
        cfg.setShowAllWarnings(options.hasShowAllWarnings());

        String[] plugins = options.getPlugins();


        PluginManager pm = new PluginManager(cfg);
        pm.start();
    }

}
