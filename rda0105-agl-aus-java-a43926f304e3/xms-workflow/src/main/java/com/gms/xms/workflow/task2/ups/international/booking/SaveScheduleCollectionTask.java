package com.gms.xms.workflow.task2.ups.international.booking;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.webship.ship.QuoteVo;
import com.gms.xms.workflow.core.Task;

public class SaveScheduleCollectionTask implements Task {

	private static final Log log = LogFactory.getLog(SaveScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
	try {
	    context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
	    QuoteVo quoteVo = GsonUtils.fromGson(context.get(Attributes.QUOTE_VO), QuoteVo.class);
	    BookingDataVo bookingDataVo = GsonUtils.fromGson(context.get(Attributes.BOOKING_DATA), BookingDataVo.class);
	    ScheduleCollectionVo scheduleCollectionVo = new ScheduleCollectionVo();
	    scheduleCollectionVo = bookingDataVo.getScheduleCollection();
	    if (StringUtils.isNotEmpty(context.get(Attributes.SCHEDULECOLLECTION_VO))) {
		scheduleCollectionVo = GsonUtils.fromGson(context.get(Attributes.SCHEDULECOLLECTION_VO), ScheduleCollectionVo.class);
		scheduleCollectionVo.setTotalWeight(Float.parseFloat(String.valueOf(quoteVo.getWeight())));
		bookingDataVo.setScheduleCollection(scheduleCollectionVo);
	    }

	    context.put(Attributes.QUOTE_VO, GsonUtils.toGson(quoteVo));
	    context.put(Attributes.BOOKING_DATA, GsonUtils.toGson(bookingDataVo));
	} catch (Exception e) {
	    context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
	    context.put(Attributes.ERROR_MESSAGE, e.getMessage());
	    log.error(e);
	    return false;
	}
	return true;
    }


}
