package fun.sherman.tlmall.dao;

import fun.sherman.tlmall.domain.Order;
import fun.sherman.tlmall.provider.OrderProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author sherman
 */
public interface OrderDao {
    @Select("select id, user_id, order_no, shipping_id, payment, payment_type, postage, status, payment_time, send_time, end_time, close_time, create_time, update_time from tlmall_order " +
            "where user_id=#{userId} and order_no=#{orderNo}")
    Order selectOrderByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") long orderNo);

    @Select("select id, user_id, order_no, shipping_id, payment, payment_type, postage, status, payment_time, send_time, end_time, close_time, create_time, update_time from tlmall_order " +
            "where order_no=#{orderNo}")
    Order selectOrderByOrderNo(long orderNo);

    @UpdateProvider(type = OrderProvider.class, method = "updateOrderProvider")
    int updateSelective(Order order);

    @Insert("insert into tlmall_order(order_no, user_id, shipping_id, payment, payment_type, postage, status, payment_time, send_time, end_time, close_time, create_time, update_time) " +
            "values(#{orderNo}, #{userId}, #{shippingId}, #{payment}, #{paymentType}, #{postage}, #{status}, #{paymentTime}, #{sendTime}, #{endTime}, #{closeTime}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Order order);

    @Select("select id, order_no, user_id, shipping_id, payment, payment_type, postage, status, payment_time, send_time, end_time, close_time, create_time, update_time from tlmall_order " +
            "where user_id=#{userId}")
    List<Order> selectOrderListByUserId(Integer userId);

    @Select("select id, order_no, user_id, shipping_id, payment, payment_type, postage, status, payment_time, send_time, end_time, close_time, create_time, update_time from tlmall_order")
    List<Order> selectAllOrder();
}
