package com.zpark.springboot02.util;

/**
 * 用于支付宝支付的工具类
 */
public class AliPayUtil {

    //支付网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do"; //沙箱网关

    //当前收钱方的支付宝appid
    public static String appId = "2016102500756104";

    //应用私钥
    public static String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCx/xzjaCQ4Gad/I2CVaHoygLz4tWArzH+0cE72kqtljyIYKeNh8FztPvdbaeB421yk5uycN/tuacubSU55mJ0Qn8lxFVLPOEUXh23xNeJ1mRR/ZCVM60niWtPyHFQyLRTo1cuehw4bpDIxCJOxCjmeZnd9k8ls+cOgxDfMmTa2ITEMZKSE0h6mYTCl+CJhRbXGa410FMNpiSrhIviU4OtvOkgroKbyeZR5qVOmrV3Gbb1wOB8ACrkZzfbrk40MoXRd/O1Aba/2iSu7Ei5F9QxmK6o+yl5MRD1KDQQGJXl/VhRxaPqGNVTZyOnbLtEfvnOG6t+5Ga3n9fSC5mpCYANfAgMBAAECggEATFGaTVcl0HgPfU/p5aX6XxUb8XezWJRCXVeaQ9b3boBEmh+7ww6QiZjp7mQjPy6vNnVdFk5anCj4iAiSgUiSb8JTWQ1PiFIULdZ/qWD1TH7qbwS9iZGJCGjxX2oOH8V8pailub8mqWtpqeSHju1Xbzd4HHv1bahZ3ODFHBDFiKlTa/S0CGStKmoxEXbiPpF+bKkPnHqMJbFDgB8cu2YlAALrTvyaizYtHHRuZRvzgvx6g45tmyyW39vsI528giPU73Cy22ZJC1tap2fK8Nw/0piBNhPto+HcJpuz2Z7T/7Os4EnEL8btIb8sptp/8sZ43uLrJ0W8u6OmArPKB0pfwQKBgQDeg+tIkb98GMmheUDHPSI9di7TgoJmD1IYaxqgYfqPIWOx68yW4dv3t4o4DdSSisMDPnmv8qFo29ZVi4letPcqt98S8+ACGiqzUIs1fi1CKqiy47ynyq4cbFjK/o1vYzn2JthCNpfosv/1cigRmh9t3tKNb07+T7DFAQOYnVpORwKBgQDMyCwakFjOgnJk1UHx4rFBAysov19XVmA9aae7HTDqcR03YBRnc+le7g4+966gK04LIUzfRuVN8TJNBI4Y6oU8PsEWt8DWsk/pd+D/MCfXzJTTAAyfS3nG2g+rAYUvQcGzxhPCLCpnzxFqIjE2kGLAXo70ppApQnvnxP6UnSW2KQKBgBhm2R+NNDWUtk/p7yuqTZ313u69CjXNClC5NiNOe17E55z0YexN+oRw8UxyX9RuOTKa4FdmQUMxkJCzC66CI/TgUytN6UvxuKsPcyS7oJT3/qYXiXHRLUTasOYaAkKr3AhdfCvOttwC/UTabwB3KWkwHWD7Sutiq9z+3nkXEHczAoGAZDLxuZG1kl4roQDkAnn8ZyYowQhamz5tWr9OzfRrkdsFA6pkcXY60zNCDzdb340w46tIlNt4qZmCFkNLUA+4X5EJI5eHqMWY6lC468RhSmsUyuV0R6FDlbPvBKp6w3O2VsNtuY2otiCQkdDnKZ6eYyghZL33PekE/ZPbSVZXNHkCgYAwiDXaLUZJXoJV0kgovI0Ahy0y2wH8PR/PoUbtijwDyn+RYaKEthAYPKOQ2QSjW7NblkJT90WbIK2TnipjEMA6JpNWaL1tFvL9eb3Hbv3puup3iaBONA1juuXd3M/XVyazH+dJl1yoYUkZe++hFN/sZlz6LVR5Zzd2dOGQgBO+4Q==";

    //返回的数据类型
    public static String format = "json";

    //字符集
    public static String charset = "UTF-8";

    //支付宝公钥（通过应用公钥在支付宝沙箱官网换取的）
    public static String alipayPulicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyBOikTna3iBOCL3mdSXjNEvzjKAcYKulx+zg+e+VzFB+khK7IDUYZh+O4tecNLTh1r2z0fNKt3jYrMzh3O9tQCQ6cS8zYYJlnIXmxfNbva/R9XOeCXQi7gaYcJd2tXngnaUSyTiYlkenwxTQc9hkl/RH/N+tBzTA3hZnQqyFBWG1YBznSFD63BF80HAq/yi2BAVpho2/hISV+NQb11I95BJIqu180+1Ii4KsohZytG3g8gShU0iLSC8j1GxR4k0vqU5io6M5V6QNcXVVoVqDiEB1LliQyjPHzbLm9HyG4ccGJ1XD+Gnz4VAYyN7N2PwzGeLs3Ni/lgAOCsMAwwSVaQIDAQAB";

    //加密方式
    public static String signType = "RSA2";

    public static String return_url = "http://localhost:8081/pay/return";

    public static String notify_url = "http://localhost:8081/pay/notify";


}
