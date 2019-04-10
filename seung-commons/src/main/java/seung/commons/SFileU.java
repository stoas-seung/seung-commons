package seung.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.InflaterOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import seung.commons.arguments.SMap;

public class SFileU {

	public byte[] inflate(byte[] data) throws IOException {
		
		byte[] byteArray = null;
		
		ByteArrayInputStream  byteArrayInputStream  = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		InflaterOutputStream  inflaterOutputStream  = null;
		
		byteArrayInputStream  = new ByteArrayInputStream(data);
		byteArrayOutputStream = new ByteArrayOutputStream();
		inflaterOutputStream  = new InflaterOutputStream(byteArrayOutputStream);
		
		IOUtils.copy(byteArrayInputStream, inflaterOutputStream);
		
		byteArray = byteArrayOutputStream.toByteArray();
		
		closeCloseable(inflaterOutputStream);
		closeCloseable(byteArrayOutputStream);
		closeCloseable(byteArrayInputStream);
		
		return byteArray;
	}
	
	public static List<String> readLines(File file, String encoding) throws IOException {
		return FileUtils.readLines(file, encoding);
	}
	
	public static String readFileToString(File file, String encoding) throws IOException {
		return FileUtils.readFileToString(file, encoding);
	}
	
	public static void write(File file, byte[] data, boolean append) throws IOException {
		FileUtils.writeByteArrayToFile(file, data, append);
	}
	
	public void closeCloseable(Closeable closeable) throws IOException {
		if(closeable != null) {
			closeable.close();
		}
	}
	
	/**
	 * @param rootFile
	 * @param match - files or directorys which name matche
	 * @param type - SCommonV._S_FILE=F: file, SCommonV._S_DIRECTORY=D: directory, SCommonV._S_FILE_AND_DIRECTORY=FD: file and directory
	 * @param depth
	 * @return
	 *   [
	 *     {
	 *       "path": "",
	 *       "extension": "",
	 *       "parentName": "",
	 *       "parentAbsolutePath": "",
	 *       "creationTime": "",
	 *       "parentPath": "",
	 *       "name": "",
	 *       "canonicalPath": "",
	 *       "length": 0,
	 *       "absolutePath": "",
	 *       "lastModified": "",
	 *       "type": ""
	 *     }
	 *   ]
	 * @throws IOException
	 */
	public static ArrayList<SMap> getFileListInfo(String pathname) throws IOException {
		return getFileListInfo(new File(pathname), null, null, 100);
	}
	public static ArrayList<SMap> getFileListInfo(File file) throws IOException {
		return getFileListInfo(file, null, null, 100);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<SMap> getFileListInfo(
			File rootFile
			, String match
			, String type
			, int depth
			) throws IOException {
		
		if(!rootFile.exists()) {
			throw new IOException("file is not exists.");
		}
		if(!rootFile.canRead()) {
			throw new IOException("file is not readable.");
		}
		
		if(type == null || type.length() == 0) {
			type = SCommonV._S_FILE_AND_DIRECTORY;
		}
		
		ArrayList fileList = new ArrayList<SMap>();
		
		if(rootFile.isFile()) {
			if(type.contains(SCommonV._S_FILE) && (match == null || match.length() == 0 || rootFile.getPath().toLowerCase().contains(match.toLowerCase()))) {
				fileList.add(getFileInfo(rootFile));
			}
		} else if(rootFile.isDirectory()) {
			if(type.contains(SCommonV._S_DIRECTORY) && (match == null || match.length() == 0 || rootFile.getPath().toLowerCase().contains(match.toLowerCase()))) {
				fileList.add(getFileInfo(rootFile));
			}
			for(File file : rootFile.listFiles()) {
				if(depth > -1) fileList.addAll(getFileListInfo(file, match, type, depth - 1));
			}
		}
		
		return fileList;
	}
	
	public static SMap getFileInfo(String pathname) throws IOException {
		return getFileInfo(new File(pathname));
	}
	public static SMap getFileInfo(File file) throws IOException {
		
		SMap fileMap = new SMap();
		
		if(file.isDirectory()) {
			fileMap.put("type", SCommonV._S_DIRECTORY);
		} else if(file.isFile()) {
			fileMap.put("type", SCommonV._S_FILE);
		} else {
			fileMap.put("type", "");
		}
		
//		fileMap.put("totalSpace"        , file.getTotalSpace());
//		fileMap.put("usableSpace"       , file.getUsableSpace());
//		fileMap.put("freeSpace"         , file.getFreeSpace());
		fileMap.put("path"              , file.getPath().replace("\\", "/"));
		fileMap.put("name"              , file.getName());
		fileMap.put("absolutePath"      , file.getAbsolutePath().replace("\\", "/"));
		fileMap.put("canonicalPath"     , file.getCanonicalPath().replace("\\", "/"));
		if(file.isFile() && file.getName().lastIndexOf(".") > -1) {
			fileMap.put("extension"     , file.getName().substring(file.getName().lastIndexOf(".") + 1));
		} else {
			fileMap.put("extension"     , "");
		}
		fileMap.put("extension"         , FilenameUtils.getExtension(file.getCanonicalPath()));
		fileMap.put("parentPath"        , file.getParent().replace("\\", "/"));
		fileMap.put("parentName"        , file.getParentFile().getName());
		fileMap.put("parentAbsolutePath", file.getParentFile().getAbsolutePath().replace("\\", "/"));
		fileMap.put("length"            , file.length());
		fileMap.put("creationTime"      , new SimpleDateFormat("yyyyMMddHHmmss").format(Files.getFileAttributeView(Paths.get(file.getAbsolutePath()), BasicFileAttributeView.class).readAttributes().creationTime().toMillis()));
		fileMap.put("lastModified"      , new SimpleDateFormat("yyyyMMddHHmmss").format(file.lastModified()));
		
		return fileMap;
	}
	
	/**
	 * desc extract directory path
	 * param path
	 * return
	 */
	public static String getDirectoryPath(String pathname) {
		return getDirectoryPath(new File(pathname));
	}
	public static String getDirectoryPath(File file) {
		if(file.isDirectory()) {
			return file.getAbsolutePath().replaceAll("\\\\", "/");
		} else {
			return file.getParentFile().getAbsolutePath().replaceAll("\\\\", "/");
		}
	}
}
