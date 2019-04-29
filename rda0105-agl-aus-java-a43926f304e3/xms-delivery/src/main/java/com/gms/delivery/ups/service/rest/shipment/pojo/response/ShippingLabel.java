package com.gms.delivery.ups.service.rest.shipment.pojo.response;

public class ShippingLabel {

	private ImageFormat ImageFormat;
	private String GraphicImage;
	private String HTMLImage;
	private String PDF417;
	
	public ImageFormat getImageFormat() {
		return ImageFormat;
	}
	public void setImageFormat(ImageFormat imageFormat) {
		ImageFormat = imageFormat;
	}
	public String getGraphicImage() {
		return GraphicImage;
	}
	public void setGraphicImage(String graphicImage) {
		GraphicImage = graphicImage;
	}
	public String getHTMLImage() {
		return HTMLImage;
	}
	public void setHTMLImage(String hTMLImage) {
		HTMLImage = hTMLImage;
	}
	public String getPDF417() {
		return PDF417;
	}
	public void setPDF417(String pDF417) {
		PDF417 = pDF417;
	}
	
	

}
