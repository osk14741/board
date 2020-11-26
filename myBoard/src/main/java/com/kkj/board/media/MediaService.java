package com.kkj.board.media;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.kkj.board.cmn.AccessKey;

@Service
public class MediaService {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired MediaDao mediaDao;
	@Autowired AccessKey ack;
	
	public List<MediaVO> doSelectList(MediaVO mediaVO) {
		return mediaDao.doSelectList(mediaVO);
	}
	
	public int doUpdate(MediaVO mediaVO) {
		return mediaDao.doUpdate(mediaVO);
	}
	
	public int doDelete(MediaVO mediaVO) {
		return mediaDao.doDelete(mediaVO);
	}
	
	public int doInsert(MediaVO mediaVO) {
		return mediaDao.doInsert(mediaVO);
	}
	
	public MediaVO doSelectOne(MediaVO mediaVO) {
		return mediaDao.doSelectOne(mediaVO);
	}
	
	public int doFileUpload(String key_name, MultipartFile multiFile) throws IllegalStateException, IOException {
		int flag = 0;
		String accessKey = ack.getACCESS_KEY();
		String secretKey = ack.getSECRET_KEY();
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		File transferFile = new File(multiFile.getOriginalFilename());
		multiFile.transferTo(transferFile);
		
		String bucket_name = "kghbucket";
		
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_NORTHEAST_2).build();
        LOG.debug("=====================");
        LOG.debug("====uploading....====");
        LOG.debug("=====================");
        try {
        	s3.putObject(bucket_name, key_name, transferFile);
        	flag = 1;
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
			return flag;
		}
        
        LOG.debug("=====================");
        LOG.debug("====end uploading====");
        LOG.debug("=====================");
        
        return flag;
	}
	
	public URL doFileDownload(String keyName){
		String bucket_name = "kghbucket";
		String accessKey = ack.getACCESS_KEY();
		String secretKey = ack.getSECRET_KEY();
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_NORTHEAST_2).build();

		LOG.debug("=====================");
        LOG.debug("==Set presigned URL==");
        LOG.debug("=====================");
        // Set the presigned URL to expire after one hour.
		// expire 기한 일정 기간 주고 지나면 만료되도록
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60 * 24 * 3;
        expiration.setTime(expTimeMillis);

        // Generate the presigned URL.
        LOG.debug("Generate the presigned URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket_name, keyName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
        LOG.debug("=====================");
        LOG.debug("Pre-Signed URL : " + url.toString());
        LOG.debug("=====================");
        return url;
	}
	
	/**
	 * 이미지 profile 크기로 수정
	 * @param multiFile
	 * @return MultipartFile
	 * @throws IOException
	 */
	public MultipartFile doResizeProfile(MultipartFile multiFile) throws IOException {

		LOG.debug("-------------------------");
		LOG.debug("-doResizeProfile-");
		LOG.debug("-------------------------");

		int w;
		int h;

		File transferFile = new File(multiFile.getOriginalFilename());
		multiFile.transferTo(transferFile);

		Image img;
		img = ImageIO.read(transferFile);

		int originalWidth = img.getWidth(null);
		int originalHeight = img.getHeight(null);
		double ratio = (double) originalHeight / (double) originalWidth;

		if (originalWidth > originalHeight) {
			w = 350;
			h = (int) (350 * ratio);
		} else if (originalWidth < originalHeight) {
			h = 350;
			w = (int) (350 / ratio);
		} else {
			w = 350;
			h = 350;
		}

		Image imgResize = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = outImg.getGraphics();
		g.drawImage(imgResize, 0, 0, null);
		g.dispose();

		File file = new File("test_resize.jpg");
		try {
			ImageIO.write(outImg, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DiskFileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, "testYong.jpg",
				(int) file.length(), file.getParentFile());

		InputStream input = new FileInputStream(file);
		OutputStream os = fileItem.getOutputStream();
		IOUtils.copy(input, os);

		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

		LOG.debug("-------------------------");
		LOG.debug("-End doResizeProfile-");
		LOG.debug("-------------------------");

		return multipartFile;
	}

	/**
	 * 이미지 thumbnail 크기로 조정
	 * @param multiFile
	 * @return MultipartFile
	 * @throws IOException
	 */
	public MultipartFile doResizeThumb(MultipartFile multiFile) throws IOException {

		LOG.debug("-------------------------");
		LOG.debug("-doResizeThumb-");
		LOG.debug("-------------------------");

		File transferFile = new File(multiFile.getOriginalFilename());
		multiFile.transferTo(transferFile);

		Image img;
		img = ImageIO.read(transferFile);

		int w = 60;
		int h = 60;

		Image imgResize = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics g = outImg.getGraphics();
		g.drawImage(imgResize, 0, 0, null);
		g.dispose();

		File file = new File("test_resize.jpg");
		try {
			ImageIO.write(outImg, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DiskFileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, "testYong.jpg",
				(int) file.length(), file.getParentFile());

		InputStream input = new FileInputStream(file);
		OutputStream os = fileItem.getOutputStream();
		IOUtils.copy(input, os);

		MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

		LOG.debug("-------------------------");
		LOG.debug("-End doResizeThumb-");
		LOG.debug("-------------------------");

		return multipartFile;
	}
}
