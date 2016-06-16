package com.tonghang.server.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tonghang.server.common.vo.IpInfoDetail;
import com.tonghang.server.exception.ErrorCode;
import com.tonghang.server.exception.ServiceException;
import com.tonghang.server.util.IPExt;

public class IpInfoDetailServiceImpl   {
	/**
	 * 第一列：国家 第二列：国家/省份（有省份信息的显示省份，没有省份信息的就也是国家）/城市（外国的部分首都不属于任何洲省的，
	 * 在第二列直接显示城市名） 第三列：城市 第四列：所有者（例如学校单位公司） 第五列：运营商/线路 第六列：城市/省份/国家中心点纬度 第七咧:经度
	 * 第八列：所在时区代表城市 第九列：所在时区 第十列：中国行政区划代码 第十一列：国际区号 第十二列：国家代码 第十三列：所在大洲
	 */
	private static final int	IDX_COUNTRY				= 0;
	private static final int	IDX_PROVINCE			= 1;
	private static final int	IDX_CITY				= 2;
	private static final int	IDX_ISP					= 4;
	private static final int	IDX_LATITUDE			= 5;
	private static final int	IDX_LONGITUDE			= 6;
	private static final int	IDX_ZONE				= 8;
	private static final int	IDX_AREACODE			= 9;
	private static final int	IDX_INTERNATIONALCODE	= 10;
	private static final int	IDX_COUNTRY_CODE		= 11;

	/* ipip数据库文件本地地址 */
	private String				ipIpDatafilePath;

	public String getIpIpDatafilePath() {
		return ipIpDatafilePath;
	}

	public void setIpIpDatafilePath(String ipIpDatafilePath) {
		this.ipIpDatafilePath = ipIpDatafilePath;
	}

	private Logger log = LoggerFactory.getLogger(getClass());

	public IpInfoDetail getInfoByIp(String ip) throws ServiceException {
		String[] ips = ip.split(",");
		// 处理被反向代理之后的多ip问题,如果xforworded
					// 没有被恶意置入虚假ip，获取到的第一个ip将是真实客户端ip
		IpInfoDetail info = new IpInfoDetail();
		for (int i = 0; i < ips.length; i++) {
			if (isIp(ips[i])) {
				info = getIpInfoDetailByIp(ips[i]);
				if ("*".equals(info.getCountryCode())) {
					continue;
				} else {
					break;
				}
			}
			if (i == (ips.length - 1)) {
				info = getIpInfoDetailByIp(ip);
			}
		}

		if (log.isDebugEnabled()) {
			log.info(info.toString());
		}
		if (ips.length > 1 && "*".equals(info.getCountryCode())) {
			info = getIpInfoDetailByIp(ips[1]);
		}
		return info;
	}

	protected void init() {
		IPExt.enableFileWatch = true; // 默认值为：false，如果为true将会检查ip库文件的变化自动reload数据
		IPExt.load(ipIpDatafilePath);
	}

	private boolean isIp(String ip) {
		ip = ip.trim();
		boolean flag = false;
		if (StringUtils.isNumeric(ip.replace(".", ""))) {
			flag = true;
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			flag = false;
		}
		return flag;
	}

	private IpInfoDetail getIpInfoDetailByIp(String ip) throws ServiceException {
		ip = ip.trim();
		if (!isIp(ip)) {
			log.error("ip地址格式有误" + ip);
			throw new ServiceException(ErrorCode.code856.getCode(), ErrorCode.code856.getHttpCode(),
					ErrorCode.code856.getDesc());
		}

		IpInfoDetail info = new IpInfoDetail();
		info.setIp(ip);
		try {
			String[] infos = IPExt.find(ip);

			if (infos != null && infos.length > 0) {

				info.setCountry(infos[IDX_COUNTRY]);
				info.setProvince(infos[IDX_PROVINCE]);
				info.setCity(infos[IDX_CITY]);
				info.setServiceProvider(infos[IDX_ISP]);
				info.setLongitude(infos[IDX_LATITUDE]);
				info.setLatitude(infos[IDX_LONGITUDE]);
				info.setTimeZone(infos[IDX_ZONE]);
				info.setAreaCode(infos[IDX_AREACODE]);
				info.setInternatinalCode(infos[IDX_INTERNATIONALCODE]);
				info.setCountryCode(infos[IDX_COUNTRY_CODE]);

			}
		} catch (Exception e) {
			log.error("IP parse Failed" + ip +","+e.getMessage());
			throw new ServiceException(ErrorCode.code856.getCode(), ErrorCode.code856.getHttpCode(),
					ErrorCode.code856.getDesc());
		}
		return info;
	}

	
	public static void main(String[] args) throws ServiceException {
	     IpInfoDetailServiceImpl ip  =new IpInfoDetailServiceImpl();
	     ip.setIpIpDatafilePath("G://mydata4vipday2.datx");
       System.out.println( ip.getInfoByIp("115.197.180.74"));
    }
}
