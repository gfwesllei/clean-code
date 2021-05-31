package com.c.refactoring.lock;

import java.util.Date;
import java.util.List;

public class UserLoginChecker {

    public static final int MAX_LOGIN_TIME_MS = 60 * 60 * 1000;

    /**
     * {@inheritDoc}.
     */
    public Lock isUserAllowedToLogin(long id, String status,
                                     boolean isFirstScreen, User user, List existingLocks) {
        Date time = new Date();

        if (existingLocks.isEmpty()) {
            return createWriteLock();
        }
        Object[] object = (Object[]) existingLocks.get(0);
        String userIdWithLock = (String) object[0];
        Date lockTimestamp = (Date) object[1];

        if (userIdWithLock == null) {
            return createWriteLock();
        }

        if (userIdWithLock.equalsIgnoreCase(user.getUserId())) {
            return createWriteLock();
        }

        long sessionDuration = time.getTime() - lockTimestamp.getTime();
        if (isFirstScreen && sessionDuration > MAX_LOGIN_TIME_MS) {
                return createWriteLock();
        }
        return createWireLockWithMsg(userIdWithLock);
    }

    private Lock createWireLockWithMsg(String userId) {

        String lockMsg = Constants.LOCK_TEXT.replace("@@USER@@",
                userId);
        Lock lck = new Lock();
        lck.setRead(true);
        lck.setLockReason(lockMsg);
        return lck;
    }

    private Lock createWriteLock() {
        Lock lck = new Lock();
        lck.setRead(false);
        return lck;
    }
}