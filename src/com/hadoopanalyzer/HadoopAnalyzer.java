/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadoopanalyzer;



import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;


/**
 *
 * @author ArunRamya
 */
public class HadoopAnalyzer {

    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
                throws IOException {
            String line = value.toString();
           // System.out.println("line is"+line);
            String[] split = line.toString().split("\\|");
            output.collect(new Text(split[0]), new Text(line));
        }
    }

    public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

        @Override
        public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output,
                Reporter reporter) throws IOException {
            while (values.hasNext()) {
                String content = values.next().toString();
                output.collect(key, new Text(content));
            }
        }
    }

    public void processFiles(File inputFile) throws Exception {
        //System.out.println("PROCESS RECEIVED**********************");
        HadoopAnalyzer hadoopAnalyzer = new HadoopAnalyzer();

        JobConf conf = new JobConf(HadoopAnalyzer.class);

        conf.set("fs.defaultFS", "hdfs://127.0.0.1:9000");
        conf.set("mapred.job.tracker", "127.0.0.1:9001");
        conf.setJobName("hadooptrans");

        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setMapperClass(Map.class);
        //conf.setCombinerClass(Reduce.class);
        conf.setReducerClass(Reduce.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        //Code for accessing HDFS file system
        FileSystem hdfs = FileSystem.get(conf);
        Path homeDir = hdfs.getHomeDirectory();
        //Print the home directory
        System.out.println("Home folder -" + homeDir);

        //Add below code For creating and deleting directory
        Path workingDir = hdfs.getWorkingDirectory();
        Path newFolderPath = new Path("/input");
        newFolderPath = Path.mergePaths(workingDir, newFolderPath);
        if (hdfs.exists(newFolderPath)) {
            hdfs.delete(newFolderPath, true); //Delete existing Directory
        }

        hdfs.mkdirs(newFolderPath);     //Create new Directory

        //Code for copying File from local file system to HDFS
        String filePath = inputFile.getAbsolutePath();
        filePath = filePath.substring(0, filePath.lastIndexOf(File.separator));
        Path localFilePath = new Path(inputFile.getAbsolutePath());
        Path hdfsFilePath = new Path(newFolderPath + "/" + inputFile.getName());
        hdfs.copyFromLocalFile(localFilePath, hdfsFilePath);

        hdfs.copyFromLocalFile(localFilePath, newFolderPath);

        FileInputFormat.addInputPath(conf, hdfsFilePath);
        FileSystem fs = FileSystem.get(conf);
        Path out = new Path("hdfs://127.0.0.1:9000/hanout");
        boolean deleted = fs.delete(out, true);
        System.out.println("Hout delete status:-" + deleted);
        FileOutputFormat.setOutputPath(conf, new Path("hdfs://127.0.0.1:9000/hanout"));
        JobClient.runJob(conf);
        //Finally copying the out file to local after job has run
        fs.copyToLocalFile(new Path("hdfs://127.0.0.1:9000/hanout/part-00000"),
                new Path("D:\\TEMP\\result.csv"));

        System.out.println("End of the program");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("MAIN RECEIVED**********************");
        /*java.util.List<VMDetails> getAllVmDetails = new com.nura.dao.impl.VMDtlsDAOImpl().getAllVMDtls();
        java.io.FileWriter fileWriter = new java.io.FileWriter(constants.Constants.FILE_HADOOP_IN_LOC);
        for (VMDetails vmDtls : getAllVmDetails) {
            fileWriter.write(vmDtls.getVmName() + "|" + vmDtls.getParentServer() + "|" + vmDtls.getVmExeStatus() + "\n");
            fileWriter.flush();
        }
        fileWriter.close();*/
        java.io.FileWriter fileWriter = new java.io.FileWriter("D:\\temp\\in.csv");
        fileWriter.write("1" + "|" + "Data" + "|" + "reduce" + "\n");
            fileWriter.flush();
        
        fileWriter.close();
        new HadoopAnalyzer().processFiles(new File("D:\\temp\\in.csv"));
    }
}
