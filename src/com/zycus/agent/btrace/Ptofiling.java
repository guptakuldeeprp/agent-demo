package com.zycus.agent.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.Profiler;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.OnTimer;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.Property;

@BTrace class Profiling {
    @Property
    Profiler swingProfiler = BTraceUtils.Profiling.newProfiler();
    
    @OnMethod(clazz="/com\\.zycus\\..*/", method="/.*/")
    void entry(@ProbeMethodName(fqn=true) String probeMethod) { 
        BTraceUtils.Profiling.recordEntry(swingProfiler, probeMethod);
    }
    
    @OnMethod(clazz="/com\\.zycus\\..*/", method="/.*/", location=@Location(value=Kind.RETURN))
    void exit(@ProbeMethodName(fqn=true) String probeMethod, @Duration long duration) { 
        BTraceUtils.Profiling.recordExit(swingProfiler, probeMethod, duration);
    }
    
    @OnTimer(5000)
    void timer() {
        BTraceUtils.Profiling.printSnapshot("Swing performance profile", swingProfiler);
    }
}
