package bit.java39.test;

/* 학습목표: 재귀호출 다루기
 * 
 */
public class RecursiveCallTest {

	public static void main(String[] args) {
		//System.out.println( sum(10) );
		test(1);
		
		/*
		System.out.print("long ");
		for(int i = 0; i < 300; i++) {
			System.out.print("t" + i + "=10L,");
		}
		*/
		
	}
	
	public static int sum(int value) {
		if (value > 0) {
			return value + sum(value - 1);
		} else {
			return value;
		}
	}
	
	public static void test(int i) {
		long t0=10L,t1=10L,t2=10L,t3=10L,t4=10L,t5=10L,t6=10L,t7=10L,t8=10L,t9=10L,t10=10L,t11=10L,t12=10L,t13=10L,t14=10L,t15=10L,t16=10L,t17=10L,t18=10L,t19=10L,t20=10L,t21=10L,t22=10L,t23=10L,t24=10L,t25=10L,t26=10L,t27=10L,t28=10L,t29=10L,t30=10L,t31=10L,t32=10L,t33=10L,t34=10L,t35=10L,t36=10L,t37=10L,t38=10L,t39=10L,t40=10L,t41=10L,t42=10L,t43=10L,t44=10L,t45=10L,t46=10L,t47=10L,t48=10L,t49=10L,t50=10L,t51=10L,t52=10L,t53=10L,t54=10L,t55=10L,t56=10L,t57=10L,t58=10L,t59=10L,t60=10L,t61=10L,t62=10L,t63=10L,t64=10L,t65=10L,t66=10L,t67=10L,t68=10L,t69=10L,t70=10L,t71=10L,t72=10L,t73=10L,t74=10L,t75=10L,t76=10L,t77=10L,t78=10L,t79=10L,t80=10L,t81=10L,t82=10L,t83=10L,t84=10L,t85=10L,t86=10L,t87=10L,t88=10L,t89=10L,t90=10L,t91=10L,t92=10L,t93=10L,t94=10L,t95=10L,t96=10L,t97=10L,t98=10L,t99=10L,t100=10L,t101=10L,t102=10L,t103=10L,t104=10L,t105=10L,t106=10L,t107=10L,t108=10L,t109=10L,t110=10L,t111=10L,t112=10L,t113=10L,t114=10L,t115=10L,t116=10L,t117=10L,t118=10L,t119=10L,t120=10L,t121=10L,t122=10L,t123=10L,t124=10L,t125=10L,t126=10L,t127=10L,t128=10L,t129=10L,t130=10L,t131=10L,t132=10L,t133=10L,t134=10L,t135=10L,t136=10L,t137=10L,t138=10L,t139=10L,t140=10L,t141=10L,t142=10L,t143=10L,t144=10L,t145=10L,t146=10L,t147=10L,t148=10L,t149=10L,t150=10L,t151=10L,t152=10L,t153=10L,t154=10L,t155=10L,t156=10L,t157=10L,t158=10L,t159=10L,t160=10L,t161=10L,t162=10L,t163=10L,t164=10L,t165=10L,t166=10L,t167=10L,t168=10L,t169=10L,t170=10L,t171=10L,t172=10L,t173=10L,t174=10L,t175=10L,t176=10L,t177=10L,t178=10L,t179=10L,t180=10L,t181=10L,t182=10L,t183=10L,t184=10L,t185=10L,t186=10L,t187=10L,t188=10L,t189=10L,t190=10L,t191=10L,t192=10L,t193=10L,t194=10L,t195=10L,t196=10L,t197=10L,t198=10L,t199=10L,t200=10L,t201=10L,t202=10L,t203=10L,t204=10L,t205=10L,t206=10L,t207=10L,t208=10L,t209=10L,t210=10L,t211=10L,t212=10L,t213=10L,t214=10L,t215=10L,t216=10L,t217=10L,t218=10L,t219=10L,t220=10L,t221=10L,t222=10L,t223=10L,t224=10L,t225=10L,t226=10L,t227=10L,t228=10L,t229=10L,t230=10L,t231=10L,t232=10L,t233=10L,t234=10L,t235=10L,t236=10L,t237=10L,t238=10L,t239=10L,t240=10L,t241=10L,t242=10L,t243=10L,t244=10L,t245=10L,t246=10L,t247=10L,t248=10L,t249=10L,t250=10L,t251=10L,t252=10L,t253=10L,t254=10L,t255=10L,t256=10L,t257=10L,t258=10L,t259=10L,t260=10L,t261=10L,t262=10L,t263=10L,t264=10L,t265=10L,t266=10L,t267=10L,t268=10L,t269=10L,t270=10L,t271=10L,t272=10L,t273=10L,t274=10L,t275=10L,t276=10L,t277=10L,t278=10L,t279=10L,t280=10L,t281=10L,t282=10L,t283=10L,t284=10L,t285=10L,t286=10L,t287=10L,t288=10L,t289=10L,t290=10L,t291=10L,t292=10L,t293=10L,t294=10L,t295=10L,t296=10L,t297=10L,t298=10L,t299=10L;
		System.out.println(i++);
		test(i);
	}

}
